# SMS-API-Micrservice
An Microservice which validates number aganist account and allows to send SMS with daily limit.

## Tech/framework used
Java, Strust2, Postgress and Redis are used.

# Code Style
## Interceptors Class
com.ms.sms.api.interceptor.HTTPMethodInterceptor -- Used to validate HTTP method.
com.ms.sms.api.interceptor.Variables -- Initialize public variables to threadlocal variable.
com.ms.sms.api.interceptor.Authorize -- Does authuthentcate request credentials and validates input data.

## URL - Action Class
/inbound/sms/ - com.ms.sms.api.action.SMSAPIAction.inbound() - does inbound operation
/outbound/sms/ - com.ms.sms.api.action.SMSAPIAction.outbound() -does outbound operation

## Variables Class
com.ms.sms.api.core.subscriber.User - validates credentials aganist DB.
com.ms.sms.api.core.subscriber.SubscriberNumber - Parent class of Sender and Receiver & valides number aganit db with account.
com.ms.sms.api.core.subscriber.Sender - sets sender data.
com.ms.sms.api.core.subscriber.Receiver - sets receiver data.

com.ms.sms.api.core.message.SMSMessage - Holds text message of sms, sender, receiver and account (credentials), it valides the textdata.
com.ms.sms.api.core.message.InBoundMessage - inBound() - initates validation whether 'to' param present in the account. Stores in Number Blocking logic using redis is handled here.
com.ms.sms.api.core.message.OutBoundMessage - outBound() - initates validation whether 'from' param present in the account. checks whether current 'from','to' pair is blocked, validate and increaments daily sms count.

## ErrorHandlers Class
com.ms.sms.api.errorhandlers.* - 1. Error4xxHandler class capture 404 and 405 error.
                                 2. ExceptionHandler class handles all thrown exception, acts as super class for Error4xxHandler
                                 3. APIErrorHandler acts as super class for ExceptionHandler and provies basic utils.
                                 
## Filter Class
com.ms.sms.api.filter.Server - Logs and cleans threadlocal for each request.

