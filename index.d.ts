declare module "react-native-play-services" {
  const GooglePlayServicesStatus = {
    AVAILABLE: 10,
    GMS_DISABLED: 20,
    GMS_NEED_UPDATE: 21,
    INVALID: 30
  } as const;
  const sync: () => Promise<void>;
  const goToSettings: () => Promise<void>;
  const goToMarket: () => Promise<void>;
}
