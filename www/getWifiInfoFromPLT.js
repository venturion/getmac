var WifiInfo = {

createEvent : function(successCallback, failureCallback) {
    cordova.exec(successCallback, failureCallback, 'WifiInfoPlugin',
            'WifiInfo', []);
}
};