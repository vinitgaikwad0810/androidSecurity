# androidSecurity
Android Security:  

Create two Android Mobile Apps. 

App1 - takes an input message (i.e. “Hello World”) from the user and App2 prints out the message to the Console viewable via ADB Logcat.  The message from App1 should be sent to App2 via Intents.

App2 should be a Service (http://developer.android.com/reference/android/app/Service.html) and should use Signature Level protection (http://developer.android.com/guide/topics/manifest/permission-element.html#plevel). 

Create App3 (Bad App) that attempts to intercept the message from App1.  Note that App3 will not be signed by the same certificate as App1 and App2.  Question: What happens when App3 is installed before App1 and App2?  Also See:  https://developer.android.com/guide/topics/security/permissions.html.
