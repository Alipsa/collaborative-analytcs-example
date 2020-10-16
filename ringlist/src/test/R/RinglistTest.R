library('hamcrest')

assertThat(nrow(customers), equalTo(5))
assertThat(nrow(validCustomers), equalTo(3))

#inout$View(customers, "customers")
#inout$View(validCustomers, "validCustomers")