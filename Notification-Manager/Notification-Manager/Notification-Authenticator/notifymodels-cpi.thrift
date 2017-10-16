namespace java org.Notification.Authenticator.stubs

include "./notifymodels.thrift"

service NotificationRequestDetailsService {
        list<notifymodels.Reviewer> getReviewers(1: required string requestID)
      
      	notifymodels.Admin getAdmin(1 : required string requestID)
      	
      	notifymodels.Request getStatus(1 : required string requestID )
 }