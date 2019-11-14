import { NativeModules, Platform } from 'react-native';

const { PlayServices } = NativeModules;

const isAndroid = Platform.OS === 'android';

PlayServices.GooglePlayServicesStatus = {
  AVAILABLE: 10,
  GMS_DISABLED: 20,
  GMS_NEED_UPDATE: 21,
  INVALID: 30
};

PlayServices.sync = async () => {
  if (!isAndroid) {
    return;
  }

  const status = PlayServices.checkPlayServicesStatus();
  switch (status) {
    case PlayServices.GooglePlayServicesStatus.GMS_DISABLED:
      PlayServices.goToSettings();
      break;
    case PlayServices.GooglePlayServicesStatus.GMS_NEED_UPDATE:
      PlayServices.goToMarket();
      break;
    case PlayServices.GooglePlayServicesStatus.AVAILABLE:
      break;
    default:
      const error = new Error(`Unhandled type: ${status}`);
      console.error(error);
      throw error;
  }
};

PlayServices.checkPlayServicesStatus = isAndroid
  ? PlayServices.checkPlayServicesStatus
  : async () => PlayServices.GooglePlayServicesStatus.AVAILABLE;

PlayServices.goToSettings = isAndroid
  ? PlayServices.goToSettings
  : async () => {};

PlayServices.goToMarket = isAndroid
  ? PlayServices.goToMarket
  : async () => {};

export default PlayServices;
