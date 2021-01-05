# react-native-play-services

[EN](https://github.com/humanscape/react-native-play-services/blob/master/README.en.md) | KO

![NPM](https://img.shields.io/npm/v/react-native-play-services?style=flat-square&logo=npm)
![LICENSE](https://img.shields.io/github/license/humanscape/react-native-play-services?style=flat-square&logo=license)
![Stars](https://img.shields.io/github/stars/humanscape/react-native-play-services?style=flat-square&logo=github)
[![Actions Status](https://github.com/humanscape/react-native-play-services/workflows/npm-auto-publish/badge.svg)](https://github.com/react-native-play-services/react-native-play-services/actions?style=flat-square)

### 사용하기 전에

이 라이브러리는 `React Native 0.60.5+` 버전에서 만들어졌습니다. 이하 버전에 대해 정상 동작을 보장할 수 없습니다. 버전을 `0.60.5` 이상으로 업그레이드한 후 사용하시는 것을 권장합니다.

[React Native 공식 문서 - Upgrading to new React Native versions](https://facebook.github.io/react-native/docs/upgrading)

### 소개

React Native에서 Google Play Services API를 사용할 수 있는 라이브러리입니다.

#### 기능
- Google Play Services를 사용할 수 있는지 검사하기
- Google Play Services를 활성화할 수 있는 설정 페이지로 이동
- Google Play Services를 업데이트할 수 있는 Play Store 링크로 이동

### 설치

#### npm 혹은 yarn을 이용한 설치

```shell
$ npm install react-native-play-services --save
```
혹은, 
```shell
$ yarn add react-native-play-services
```

#### 수동 설치 (자동 설치가 안 될 시)

1. `android/app/src/main/java/[...]/MainApplication.java` 파일을 엽니다.
  - 파일 최상단에 `import io.humanscape.opensources.playservices.PlayServicesPackage;` 를 작성합니다.
  - `getPackages()` 메서드에서 반환하는 배열 안에 `new PlayServicesPackage()`를 추가합니다.
2. `android/settings.gradle`에 아래 구문을 추가합니다.
  	```gradle
  	include ':react-native-play-services'
  	project(':react-native-play-services').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-play-services/android')
  	```
3. 아래 구문을 `android/app/build.gradle` 파일의 `dependencies` 스코프 안에 추가합니다.:
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
			- Google Play Services API를 사용할 수 있습니다.
		- `GooglePlayServicesStatus.GMS_DISABLED`
			- Google Play Services가 비활성화되어 있습니다.
			- 설정에서 활성화를 해주어야 합니다.
		- `GooglePlayServicesStatus.GMS_NEED_UPDATE`
			- Google Play Services를 업데이트해야 합니다.
			- Play Store에서 업데이트를 진행해야 합니다.
		- `GooglePlayServicesStatus.INVALID`
			- Google Play Services의 상태를 알 수 없습니다.

- Functions
	- `sync()`
		- ```typescript
			sync: (params: { onGmsDisabled?: () => {}, onGmsNeedUpdate?: () => {} }) => Promise<void>;
			```
		- Google Play Services 상태를 최신으로 유지하는 동작을 실행합니다.
		- onGmsDisabled가 없을 시, `goToSettings()` 함수를 실행합니다.
		- onGmsNeedUpdate가 없을 시 `goToMarket()` 함수를 실행합니다.
	- `checkPlayServicesStatus()`
		- ```typescript
			checkPlayServicesStatus: () => Promise<GooglePlayServicesStatus>;
			```
		- Google Play Services 상태를 체크하고, `GooglePlayServicesStatus`를 반환합니다.
	- `goToSetting()`
		- ```typescript
			goToSetting: () => Promise<void>;
			```
		- Google Play Services를 활성화할 수 있는 앱 설정으로 진입합니다.
	- `goToMarket()`
		- ```typescript
			goToMarket: () => Promise<void>;
			```
		- Google Play Services를 업데이트할 수 있는 Play Store 링크를 엽니다.
