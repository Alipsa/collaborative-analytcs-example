library('hamcrest')
library('se.alipsa:phone-number')

assertThat(validatePhoneNumber("0706655195"), equalTo(TRUE))

numbers <- c("08102823", "0123", "+46706655195")

validationList <- validatePhoneNumbers(numbers)
assertThat(validationList, equalTo(c(TRUE, FALSE, TRUE)))