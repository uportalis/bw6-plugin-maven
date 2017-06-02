package com.tibco.bw.maven.plugin.osgi.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.maven.shared.utils.StringUtils;

public class VersionParser {
    protected static SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
    public static final String QUALIFIER = "qualifier";

	public static Version parseVersion(String version) {
		if (version == null) {
				return Version.EMPTYVERSION;
		}
		return new Version(version);
	}

	public static String getcalculatedOSGiVersion(String mavenProjectVersion) {
		Version version = parseVersion(mavenProjectVersion.replace("-SNAPSHOT", ""));
		String calcQualifier = calculateQualifier(version.getQualifier());
		return version.getMajor() + "." + version.getMinor() + "." + version.getMicro() + "." + calcQualifier;
	}

    private static String calculateQualifier(String qualifier) {
    	if (qualifier.isEmpty() || QUALIFIER.equals(qualifier)) {
            Date timestamp = new Date();
            return format.format(timestamp);
    	}
    	return qualifier;
    }
}
