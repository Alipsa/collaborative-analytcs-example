library("se.alipsa:phone-number")

# typically you would fetch data from a database or file
# since this is just an example we create the data here instead
fetchCustomers <- function() {
  cNames <- c("Kalle", "Viktor", "Anna", "Stina", "Bob")
  cNums <- c("08102823", "123", "+46701234567", "+46012", "0722402378")
  customers <- data.frame("Name" = cNames, "Phone" = cNums, stringsAsFactors=FALSE)
  return(customers)
}

customers <- fetchCustomers()

validCustomers <-  subset(customers, validatePhoneNumbers(customers$Phone))

#inout$View(customers, "customers")
#inout$View(validCustomers, "validCustomers")