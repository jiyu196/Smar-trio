package service;

public class DeviceInfoService {
	
	
	private static DeviceInfoService deviceInfoService = new DeviceInfoService();
	private DeviceInfoService() {}
	public static DeviceInfoService getInstance() {
		return deviceInfoService;
	}
	
	
	
}
