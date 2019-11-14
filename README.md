# react-native-play-services

## Getting started

`$ npm install react-native-play-services --save`

### Mostly automatic installation

`$ react-native link react-native-play-services`

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainApplication.java`
  - Add `import io.humanscape.opensources.playservices.PlayServicesPackage;` to the imports at the top of the file
  - Add `new PlayServicesPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-play-services'
  	project(':react-native-play-services').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-play-services/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-play-services')
  	```


## Usage
```javascript
import PlayServices from 'react-native-play-services';

// TODO: What to do with the module?
PlayServices;
```
