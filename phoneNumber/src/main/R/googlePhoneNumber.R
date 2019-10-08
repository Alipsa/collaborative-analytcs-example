import(com.google.i18n.phonenumbers.PhoneNumberUtil)

phoneNumber <- "0701234566"
region <- ""
util <- PhoneNumberUtil$getInstance() 
print(paste("phoneNumber type = ", typeof(phoneNumber), "; region type = ", typeof(region)))
phoneNum <- util$parseAndKeepRawInput(phoneNumber, region)
print(paste("phone number", phoneNumber, "is valid =", util$isValidNumber(phoneNum)))