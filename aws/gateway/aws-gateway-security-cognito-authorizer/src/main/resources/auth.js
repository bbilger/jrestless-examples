var Auth = (function() {
	return {
		signUp: function(userPoolId, clientId, email, password,
				successHandler, errorHandler) {
			var poolData = {
				UserPoolId : userPoolId,
				ClientId : clientId
			};
			var userPool = new AWSCognito.CognitoIdentityServiceProvider.CognitoUserPool(
					poolData);

			var attributeList = [];

			var dataEmail = {
				Name : 'email',
				Value : email
			};

			var attributeEmail = new AWSCognito.CognitoIdentityServiceProvider.CognitoUserAttribute(
					dataEmail);

			attributeList.push(attributeEmail);

			userPool.signUp(email, password, attributeList, null, function(err,
					result) {
				if (err) {
					if (typeof errorHandler === 'function') {
						errorHandler(err);
					} else {
						console.log('ERR: signup failed with: ' + err);
					}
					return;
				}
				cognitoUser = result.user;
				if (typeof successHandler === 'function') {
					successHandler(cognitoUser);
				} else {
					console.log('user name is ' + cognitoUser.getUsername());
				}
			})
		},
		confirm: function(userPoolId, clientId, email, confirmationCode,
				successHandler, errorHandler) {
			var poolData = {
				UserPoolId : userPoolId,
				ClientId : clientId
			};

			var userPool = new AWSCognito.CognitoIdentityServiceProvider.CognitoUserPool(
					poolData);
			var userData = {
				Username : email,
				Pool : userPool
			};

			var cognitoUser = new AWSCognito.CognitoIdentityServiceProvider.CognitoUser(
					userData);
			cognitoUser.confirmRegistration(confirmationCode, true, function(
					err, result) {
				if (err) {
					alert(err);
					return;
				}
				if (err) {
					if (typeof errorHandler === 'function') {
						errorHandler(result);
					} else {
						console.log('ERR: confirmation failed with: ' + err);
					}
					return;
				}
				if (typeof successHandler === 'function') {
					successHandler(result);
				} else {
					console.log('confirmation result is: ' + result);
				}
			});
		},
		login: function(userPoolId, clientId, email, password, successHandler,
				errorHandler) {
			var authenticationData = {
				Username : email,
				Password : password,
			};
			var authenticationDetails = new AWSCognito.CognitoIdentityServiceProvider.AuthenticationDetails(
					authenticationData);
			var poolData = {
				UserPoolId : userPoolId,
				ClientId : clientId
			};
			var userPool = new AWSCognito.CognitoIdentityServiceProvider.CognitoUserPool(
					poolData);
			var userData = {
				Username : email,
				Pool : userPool
			};
			var cognitoUser = new AWSCognito.CognitoIdentityServiceProvider.CognitoUser(
					userData);
			cognitoUser.authenticateUser(authenticationDetails, {
				onSuccess : function(result) {
					if (typeof successHandler === 'function') {
						successHandler(result);
					} else {
						console.log('access token: '
								+ result.getAccessToken().getJwtToken());
						console.log('idToken: ' + result.idToken.jwtToken);
					}
				},
				onFailure : function(err) {
					if (typeof errorHandler === 'function') {
						errorHandler(err);
					} else {
						console.log('ERR: login failed with: ' + err);
					}
				}
			});
		}
	}
})();