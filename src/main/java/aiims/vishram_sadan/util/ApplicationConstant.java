package aiims.vishram_sadan.util;

import java.util.Map;

public class ApplicationConstant {
       public static final String roleUser = "ROLE_USER";
	   public static final String roleAdmin = "ROLE_ADMIN";
	   public static final String roleStaff = "ROLE_STAFF";
	   public static final String roleReferrer = "ROLE_REFERRER";
	   public static final String roleReceptionist = "ROLE_RECEPTIONIST";
	   public static final String roleSuperAdmin = "ROLE_SUPER_ADMIN";
	   public static final String statusActive = "ACTIVE";
	   public static final String statusNew = "NEW";
	   public static final String staff = "STAFF";
	   public static final String nonStaff = "NON_STAFF";
	   public static final String roomAvailable = "AVAILABLE";
	   public static final String roomNotAvailable = "NOT_AVAILABLE";
	   public static final String roomDisabled = "DISABLED";
	   
	   public static final String bookingPending = "PENDING";
	   public static final String bookingConfirm = "CONFIRM";
	   public static final String bookingReminder1 = "REMINDER1";
	   public static final String bookingReminder2 = "REMINDER2";
	   public static final String bookingNotInterested = "NOT_INTERESTED";
	   public static final String bookingClose = "CLOSE";
	   public static final String bookingDiscard="DISCARD";
	   
	   
	   public static final String searchByUHID = "UHID";
	   public static final String searchByContactNo = "ContactNo";
	   public static final String searchByRequestId = "RequestId";
	   public static final Map<Integer, String> floorMap = Map.of(
		        0, "A: Ground Floor",
		        1, "B: First Floor",
		        2, "C: Second Floor",
		        3, "D: Third Floor",
		        4, "E: Fourth Floor"
	   );
	   public static final Map<Integer, String> floorIndex = Map.of(
		        0, "A",
		        1, "B",
		        2, "C",
		        3, "D",
		        4, "E"
	   );
	   public static final Map<Integer, Boolean> selectedFloors = Map.of(
		        0, true,
		        1, true,
		        2, true,
		        3, true,
		        4, true
	   );
	   public static final String postMethod = "POST";
	   public static final String getMethod = "GET";
	   public static final String trueValue = "TRUE";
}
