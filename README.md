# SMS-API-Micrservice
An Microservice which validates number aganist account and allows to send SMS with daily limit.

## Tech/framework used
Java, Junit, Strust2, Postgress and Redis are used.

## InBound/OutBound Redis Keys
1. Param_FROM + "_" + Param_TO + "_STOP" - this key is used to store in redis to block the number.
2. Param_FROM + "_" + Param_TO + "_COUNT" - this key is used to store in redis to validate dalily sms limit.

## Unit Testing and Integration Testing
1. com.ms.sms.api.tester.TestInvoker -- invoking main(String[]) will do both unit and integration testing.
All unit and inetrgation test cases are mapped in enum in respective CASES classes. The same unit test cases are used for integration testing.

# Code Style
## Interceptors Class
1. com.ms.sms.api.interceptor.HTTPMethodInterceptor -- Used to validate HTTP method.
2. com.ms.sms.api.interceptor.Variables -- Initialize public variables to threadlocal variable.
3. com.ms.sms.api.interceptor.Authorize -- Does authuthentcate request credentials and validates input data.

## URL - Action Class
1. /inbound/sms/ - com.ms.sms.api.action.SMSAPIAction.inbound() - does inbound operation.
2. /outbound/sms/ - com.ms.sms.api.action.SMSAPIAction.outbound() - does outbound operation.

## Variables Class
1. com.ms.sms.api.core.subscriber.User - validates credentials aganist DB.
2. com.ms.sms.api.core.subscriber.SubscriberNumber - Parent class of Sender and Receiver & valides number aganit db with account.
3. com.ms.sms.api.core.subscriber.Sender - sets sender data.
4. com.ms.sms.api.core.subscriber.Receiver - sets receiver data.

5. com.ms.sms.api.core.message.SMSMessage - Holds text message of sms, sender, receiver and account (credentials), it valides the textdata.
6. com.ms.sms.api.core.message.InBoundMessage - inBound() - initates validation whether 'to' param present in the account. Stores in Number Blocking logic using redis is handled here.
7. com.ms.sms.api.core.message.OutBoundMessage - outBound() - initates validation whether 'from' param present in the account. checks whether current 'from','to' pair is blocked, validate and increaments daily sms count.

## ErrorHandlers Class
1. com.ms.sms.api.errorhandlers.* - A. Error4xxHandler class capture 404 and 405 error.
                                 B. ExceptionHandler class handles all thrown exception, acts as super class for Error4xxHandler
                                 C. APIErrorHandler acts as super class for ExceptionHandler and provies basic utils.
                                 
## Filter Class
1. com.ms.sms.api.filter.Server - Logs and cleans threadlocal for each request.

