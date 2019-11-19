import { NativeModules, Platform } from 'react-native';

const isAndroid = Platform.OS === 'android';

const PlayServices = isAndroid ? NativeModules.PlayServices : {};

PlayServices.GooglePlayServicesStatus = {
  AVAILABLE: 10,
  GMS_DISABLED: 20,
  GMS_NEED_UPDATE: 21,
  INVALID: 30
};

PlayServices.checkPlayServicesStatus = isAndroid
  ? PlayServices.checkPlayServicesStatus
  : async () => PlayServices.GooglePlayServicesStatus.AVAILABLE;

PlayServices.goToSetting = isAndroid
  ? PlayServices.goToSetting
  : async () => {};

PlayServices.goToMarket = isAndroid
  ? PlayServices.goToMarket
  : async () => {};

PlayServices.sync = async ({
  onGmsDisabled = PlayServices.goToSetting,
  onGmsNeedUpdate = PlayServices.goToMarket,
}) => {
  if (!isAndroid) {
    return;
  }

  const status = PlayServices.checkPlayServicesStatus();
  switch (status) {
    case PlayServices.GooglePlayServicesStatus.GMS_DISABLED:
      onGmsDisabled();
      break;
    case PlayServices.GooglePlayServicesStatus.GMS_NEED_UPDATE:
      onGmsNeedUpdate();
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
