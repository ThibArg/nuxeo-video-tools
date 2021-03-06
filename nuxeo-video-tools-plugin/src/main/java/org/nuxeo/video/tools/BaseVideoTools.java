/*
 * (C) Copyright 2014 Nuxeo SA (http://nuxeo.com/) and contributors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *     Thibaud Arguillere
 */

package org.nuxeo.video.tools;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.nuxeo.ecm.core.api.Blob;
import org.nuxeo.ecm.platform.picture.api.BlobHelper;
import org.nuxeo.runtime.api.Framework;

/**
 * Umbrella class with basic utilities
 *
 * @since 7.1
 */
public abstract class BaseVideoTools {

    protected Blob blob;
    
    private static final File VIDEOTOOLS_TEMP_DIR = new File(System.getProperty("java.io.tmpdir") + "NuxeoVideoTools");
    
    private static String VIDEOTOOLS_TEMP_DIR_PATH;
    
    public BaseVideoTools(Blob inBlob) {
        blob = inBlob;
    }

    /**
     * Return the java File holding the blob. If the blob was not backed-up by a
     * File, create a temporary one
     * 
     * @return the java File holding the blob
     * @throws IOException
     *
     * @since 7.1
     */
    public File getBlobFile() throws IOException {
        
        return VideoToolsUtilities.getBlobFile(blob);
    }
    
    public String getTempDirectoryPath() {
        
        if(VIDEOTOOLS_TEMP_DIR_PATH == null) {
            synchronized (VIDEOTOOLS_TEMP_DIR) {
                if(VIDEOTOOLS_TEMP_DIR_PATH == null) {
                    VIDEOTOOLS_TEMP_DIR.mkdir();
                    VIDEOTOOLS_TEMP_DIR_PATH = VIDEOTOOLS_TEMP_DIR.getAbsolutePath();
                }
            }
        }
        
        return VIDEOTOOLS_TEMP_DIR_PATH;
    }

}