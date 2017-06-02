package com.tibco.bw.maven.plugin.osgi.helpers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.Attributes;
import java.util.jar.Attributes.Name;
import java.util.jar.Manifest;

import org.apache.maven.project.MavenProject;

import com.tibco.bw.maven.plugin.utils.Constants;

public class ManifestWriter {

    public static File updateManifest(MavenProject project , Manifest mf) throws IOException {
        Attributes attributes = mf.getMainAttributes();
        System.out.println("UpdateManifest Method in");
        System.out.println("Update Attribute");
        
        String version = project.getVersion();
    	
    	String qualifiedVersion = VersionParser.getcalculatedOSGiVersion(version);
    	System.out.println("The OSGi version is " + qualifiedVersion + " for Maven version of " + version);
    	attributes.putValue(Constants.BUNDLE_VERSION, qualifiedVersion);
    	
        File mfile = new File(project.getBuild().getDirectory(), "MANIFEST.MF");
        mfile.getParentFile().mkdirs();
        BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(mfile));
        try {
            mf.write(os);
        } finally {
        	if(os != null) {
        		os.close();	
        	}
        }
        return mfile;
    }
}
