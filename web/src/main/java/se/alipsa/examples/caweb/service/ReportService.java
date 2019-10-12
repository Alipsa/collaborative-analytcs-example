package se.alipsa.examples.caweb.service;

import org.renjin.eval.Context;
import org.renjin.primitives.Types;
import org.renjin.script.RenjinScriptEngine;
import org.renjin.script.RenjinScriptEngineFactory;
import org.renjin.sexp.*;
import org.springframework.stereotype.Service;
import se.alipsa.examples.caweb.model.Customer;
import se.alipsa.ringlist.ScriptLoader;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

  RenjinScriptEngineFactory factory = new RenjinScriptEngineFactory();
  RenjinScriptEngine engine = factory.getScriptEngine();

  public List<Customer> getRingList() throws java.io.IOException, javax.script.ScriptException {

    try (InputStreamReader in = new InputStreamReader(ScriptLoader.getScript().openStream())) {
      engine.eval(in);
      Environment global = engine.getSession().getGlobalEnvironment();
      Context topContext = engine.getSession().getTopLevelContext();
      ListVector df = (ListVector) global.getVariable(topContext, "validCustomers");
      List<Customer> validCustomers = new ArrayList<>();
      List<List<Object>> rowList = toRowlist(df);
      for (List<Object> row : rowList) {
        Customer cust = new Customer();
        cust.setName(row.get(0) + "");
        cust.setPhone(row.get(1) + "");
        validCustomers.add(cust);
      }
      return validCustomers;
    }
  }

  public List<List<Object>> toRowlist(ListVector df) {
    List<Vector> table = new ArrayList<>();
    for (SEXP col : df) {
      Vector column = (Vector) col;
      table.add(column);
    }
    List<List<Object>> rowList = transpose(table);
    return rowList;
  }

  public List<List<Object>> transpose(List<Vector> table) {
    List<List<Object>> ret = new ArrayList<>();
    final int N = table.get(0).length();
    for (int i = 0; i < N; i++) {
      List<Object> row = new ArrayList<>();
      for (Vector col : table) {
        if (Types.isFactor(col)) {
          AttributeMap attributes = col.getAttributes();
          Map<Symbol, SEXP> attrMap = attributes.toMap();
          Symbol s = attrMap.keySet().stream().filter(p -> "levels".equals(p.getPrintName())).findAny().orElse(null);
          Vector vec = (Vector) attrMap.get(s);
          row.add(vec.getElementAsObject(col.getElementAsInt(i) - 1));
        } else {
          row.add(col.getElementAsObject(i));
        }
      }
      ret.add(row);
    }
    return ret;
  }
}