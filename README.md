# sms-api-micrservice
An Microservice which validates number aganist account and allows to send SMS with daily limit.

## Tech/framework used
Java, Strust2, Postgress and Redis are used.

# Code Style
## Interceptors
com.ms.sms.api.interceptor.HTTPMethodInterceptor -- Used to validate HTTP method.
com.ms.sms.api.interceptor.Variables -- Initialize public variables to threadlocal variable.
com.ms.sms.api.interceptor.Authorize -- Does authuthentcate request credentials and validates input data.

## URL - Action Class
/inbound/sms/ - com.ms.sms.api.action.SMSAPIAction.inbound() - does inbound operation
/outbound/sms/ - com.ms.sms.api.action.SMSAPIAction.outbound() -does outbound operation

## Variables Class
com.ms.sms.api.core.subscriber.User - validates credentials aganist DB.
com.ms.sms.api.core.subscriber.Sender - Checks whether the number is valid.
com.ms.sms.api.core.subscriber.Receiver - Checks whether the number is valid.
com.ms.sms.api.core.subscriber.SubscriberNumber - Parent classs
