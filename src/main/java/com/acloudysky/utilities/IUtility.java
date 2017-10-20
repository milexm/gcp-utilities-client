/**
 * 
 */
package com.acloudysky.utilities;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


import com.google.api.services.storage.StorageScopes;
import com.google.api.services.youtube.YouTubeScopes;
import com.google.api.services.bigquery.BigqueryScopes;
import com.google.api.services.drive.DriveScopes; 


/**
 * Defines fields and methods to implement the Utility class.
 * @see Utility
 * @author Michael Miele
 *
 */
public interface IUtility {
	

	// The divider length used in displaying menu and other.
	int DIVIDER_LENGTH = 66;
		
	// OS specific new line.
	String newline = System.getProperty("line.separator");
	
	
	/**
	 * Environment parameters such as OS name, user's home directory. 
	 */
	ArrayList<String> environment = new  ArrayList<String>(
			Arrays.asList(
					System.getProperty("os.name"), 		// OS name.
					System.getProperty("user.home")		// User home directory.
			)
	);
	
	
	
	/**
	 * Google BigQuery allowed scopes. 
	 * The values for the scopes can be found at 
	 * https://developers.google.com/resources/api-libraries/documentation/bigquery/v2/java/latest/com/google/api/services/bigquery/BigqueryScopes.html
	 */
	HashMap<String, String> bigqueryScopes = new HashMap<String, String>()
	{ 
		// Avoid compiler warning. 
		private static final long serialVersionUID = 1L;
		// Service scopes
		{
			put("bigquery", BigqueryScopes.BIGQUERY); 
			put("bigquery_insertData", BigqueryScopes.BIGQUERY_INSERTDATA); 
			put("bigquery_cloud_platform", BigqueryScopes.CLOUD_PLATFORM); 
			put("bigquery_cloud_platform_R", BigqueryScopes.CLOUD_PLATFORM_READ_ONLY); 
			put("bigquery_devStorage_FC", BigqueryScopes.DEVSTORAGE_FULL_CONTROL); 
			put("bigquery_devStorage_R", BigqueryScopes.DEVSTORAGE_READ_ONLY); 
			put("bigquery_devStorage_RW", BigqueryScopes.DEVSTORAGE_READ_WRITE); 
			
		}
		
	};
	
	/**
	 * Google Youtube allowed scopes. 
	 * The values for the scopes can be found at 
	 * https://developers.google.com/resources/api-libraries/documentation/youtube/v3/java/latest/com/google/api/services/youtube/YouTubeScopes.html
	 */
	HashMap<String, String> youtubeScopes = new HashMap<String, String>()
	{ 
		// Avoid compiler warning. 
		private static final long serialVersionUID = 1L;
		// Service scopes
		{
			put("youtube", YouTubeScopes.YOUTUBE);
			put("youtube_ssl", YouTubeScopes.YOUTUBE_FORCE_SSL);
			put("youtube_read", YouTubeScopes.YOUTUBE_READONLY);
			put("youtube_upload", YouTubeScopes.YOUTUBE_UPLOAD);
			put("youtube_partner", YouTubeScopes.YOUTUBEPARTNER);
			put("youtube_partner_chnl_audit", YouTubeScopes.YOUTUBEPARTNER_CHANNEL_AUDIT);
		}
		
	};
	
	
	/**
	 * Google Drive allowed scopes. 
	 * The values for the scopes can be found at 
	 * https://developers.google.com/resources/api-libraries/documentation/drive/v2/java/latest/com/google/api/services/drive/DriveScopes.html
	 */
	HashMap<String, String> driveScopes = new HashMap<String, String>()
	{ 
		// Avoid compiler warning. 
		private static final long serialVersionUID = 1L;
		// Service scopes
		{
			put("drive", DriveScopes.DRIVE); 
			put("drive_appdata", DriveScopes.DRIVE_APPDATA); 
			put("drive_file", DriveScopes.DRIVE_FILE); 
			put("drive_metadata", DriveScopes.DRIVE_METADATA); 
			put("drive_metadata_R", DriveScopes.DRIVE_METADATA_READONLY); 
			put("drive_photos_R", DriveScopes.DRIVE_PHOTOS_READONLY); 
			put("drive_R", DriveScopes.DRIVE_READONLY); 
			put("drive_scripts", DriveScopes.DRIVE_SCRIPTS); 
		}
		
	};
	
	/**
	 * Google Storage allowed scopes.  
	 * The values for the scopes can be found at 
	 * <a href="https://developers.google.com/resources/api-libraries/documentation/storage/v1beta2/java/latest/com/google/api/services/storage/StorageScopes.html" target="_blank">Class StorageScopes</a>.
	 */
	HashMap<String, String> storageScopes = new HashMap<String, String>()
	{ 
		// Avoid compiler warning. 
		private static final long serialVersionUID = 1L;
		// Service scopes
		{
			put("cloud_platform", StorageScopes.CLOUD_PLATFORM); 
			put("cloud_platform_R", StorageScopes.CLOUD_PLATFORM_READ_ONLY); 
			put("dev_storage_FC", StorageScopes.DEVSTORAGE_FULL_CONTROL); 
			put("dev_storage_R", StorageScopes.DEVSTORAGE_READ_ONLY); 
			put("dev_storage_RW", StorageScopes.DEVSTORAGE_READ_WRITE); 
		}
		
		
	};
	

	
}

