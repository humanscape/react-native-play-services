# react-native-play-services

EN | [KO](https://github.com/humanscape/react-native-play-services/blob/master/README.md)

![NPM](https://img.shields.io/npm/v/react-native-play-services?style=flat-square&logo=npm)
![LICENSE](https://img.shields.io/github/license/humanscape/react-native-play-services?style=flat-square&logo=license)
![Stars](https://img.shields.io/github/stars/humanscape/react-native-play-services?style=flat-square&logo=github)
[![Actions Status](https://github.com/humanscape/react-native-play-services/workflows/npm-auto-publish/badge.svg)](https://github.com/react-native-play-services/react-native-play-services/actions?style=flat-square)

### Notes

This library was made based on `React Native 0.60.5+`. It's not recommended for under `0.60.5`, so upgrade your React Native version to `0.60.5` or later.

[React Native Official Docs - Upgrading to new React Native versions](https://facebook.github.io/react-native/docs/upgrading)

### Introduction

This library can to use Google Play Services API in React Native.

#### Features
- Determine can to use Google Play Services.
- Go to the Settings page to enable Google Play Services
- Go to the Play Store link to update Google Play Services

### Install

#### Install via npm or yarn

```shell
$ npm install react-native-play-services --save
```
or, 
```shell
$ yarn add react-native-play-services
```

#### Manual Install

1. Open `android/app/src/main/java/[...]/MainApplication.java`.
  - Put `import io.humanscape.opensources.playservices.PlayServicesPackage;` at the top of file.
  - Add `new PlayServicesPackage()` in array returns from `getPackages()` method.
2. Add these lines to `android/settings.gradle`.
  	```gradle
  	include ':react-native-play-services'
  	project(':react-native-play-services').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-play-services/android')
  	```
3. Add this line to `dependencies` scope in `android/app/build.gradle`.:
  	```gradle
      compile project(':react-native-play-services')
  	```


### API
```javascript
import PlayServices from 'react-native-play-services';

// Full-set API

PlayServices.sync();


// Customizable API

(async () => {
  const status = await PlayServices.checkPlayServicesStatus();
  switch (status) {
    case PlayServices.GooglePlayServicesStatus.GMS_DISABLED:
      PlayServices.goToSetting();
      break;
    // handle anything.
  }
})();
```

- Constants
	- `GooglePlayServicesStatus`
		- `GooglePlayServicesStatus.AVAILABLE`
			- Can use Google Play Services.
		- `GooglePlayServicesStatus.GMS_DISABLED`
			- Google Play Services is disabled.
			- Need to enable at app settings.
		- `GooglePlayServicesStatus.GMS_NEED_UPDATE`
			- Google Play Services is outdated.
			- Need to update at Play Store.
		- `GooglePlayServicesStatus.INVALID`
			- Google Play Services status is unknown.

- Functions
	- `sync()`
		- ```typescript
			sync: (params: { onGmsDisabled?: () => {}, onGmsNeedUpdate?: () => {} }) => Promise<void>;
			```
		- Perform all of actions for keeping Google Play Services status up to date.
		- `onGmsDisabled` is not specified, it will execute `goToSettings()`.
		- `onGmsNeedUpdate` is not specified, it will execute `goToMarket()`.
	- `checkPlayServicesStatus()`
		- ```typescript
			checkPlayServicesStatus: () => Promise<GooglePlayServicesStatus>;
			```
		- Check Google Play Services status, and return `GooglePlayServicesStatus`.
	- `goToSetting()`
		- ```typescript
			goToSetting: () => Promise<void>;
			```
		- Go to app settings can enable Google Play Services.
	- `goToMarket()`
		- ```typescript
			goToMarket: () => Promise<void>;
			```
		- Open Play Store link can update Google Play Services.
