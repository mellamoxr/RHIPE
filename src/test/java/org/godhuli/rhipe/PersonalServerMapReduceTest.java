package org.godhuli.rhipe;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DataInputBuffer;
import org.apache.hadoop.io.WritableUtils;
import org.godhuli.rhipe.PersonalServer;
import org.godhuli.rhipe.RHMR;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Arrays;

/**
 * User: perk387
 * Date: 1/7/14
 */
public class PersonalServerMapReduceTest extends PersonalServerBase {

    private String configurationBinaryFile = "configuration-backup.bin";
    private String configurationXmlFile = "configuration-backup.xml";

    @Before
    public void setup() throws Exception{
//        stageHdfsData();
    }
    
    @Test
    public void testSetup() throws Exception {
        System.out.println("done");
    }
    
    @Test
    public void testRhex() throws Exception {
        PersonalServer personalServer = new PersonalServer();
        personalServer.rhex(getZonfFile(),getHadoopConfiguration());
    }

    @Test
    public void testRHMRMain() throws Exception {
        RHMR.fmain(new String[]{getZonfFile()});
    }


    private void stageHdfsData() throws Exception{
        
        FileSystem fs = FileSystem.get(getHadoopConfiguration());
        
        Path testDataDirPath = new Path("hdfs://localhost:9000/tmp/rhipeTest/irisData");
        
        if(!fs.exists(testDataDirPath)){
            fs.mkdirs(testDataDirPath);
        }
        
        Path srcPath = new Path("file://" + localDataDir + "/hdfs/tmp/rhipeTest/irisData/part_1");
        fs.copyFromLocalFile(srcPath,testDataDirPath);
    }
}
