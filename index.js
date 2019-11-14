import { NativeModules } from 'react-native';

const { PlayServices } = NativeModules;

PlayServices.GooglePlayServicesStatus = {
  AVAILABLE: 10,
  GMS_DISABLED: 20,
  GMS_NEED_UPDATE: 21,
  INVALID: 30
};

PlayServices.sync = async () => {
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

export default PlayServices;
