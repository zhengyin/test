package com.izhengyin.test.hadoop;

import org.apache.avro.AvroRuntimeException;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.IndexedRecord;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecord;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.AvroFSInput;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengyin  <zhengyin.name@gmail.com>
 * @date Created on 2019/4/18 9:42 AM
 */

public class AvroWriterSupport<T extends IndexedRecord>   {
    private final Logger logger = LoggerFactory.getLogger(AvroWriterSupport.class);
    private final Logger fileOffsetLogger = LoggerFactory.getLogger("FileOffset");
    private final DatumWriter<T> datumWriter = new GenericDatumWriter<>();
    private DataFileWriter<T> dataFileWriter;
    private FSDataOutputStream fsDataOutputStream;
    private Configuration hdfsConfiguration;
    private Path filePath;
    private boolean closing = false;
    private final Schema schema ;
    public AvroWriterSupport(Configuration configuration , Path filePath , Schema schema){
        this.hdfsConfiguration = configuration;
        this.filePath = filePath;
        this.schema = schema;
    }
    /**
     *
     * @param partition  kafka分区
     * @param offset offset
     * @param record 数据记录
     * @throws IOException
     */
    public void write(int partition , long offset , T record) throws IOException {

        if(closing){
            while (closing){
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                }catch (InterruptedException e){}
            }
        }

        if(this.dataFileWriter == null){
            open();
            fileOffsetLogger.info(this.filePath.toString()+" # "+partition+" at "+offset);
        }
        dataFileWriter.append(record);
        logger.info(this.filePath+" "+dataFileWriter.isFlushOnEveryBlock());
    }

    public void close() throws IOException{
        closing = true;
        try{
            if(fsDataOutputStream != null){
                logger.info("fsDataOutputStream.flush");
                fsDataOutputStream.flush();
            }
            if(dataFileWriter != null){
                logger.info("dataFileWriter.flush");
                dataFileWriter.flush();
            }
        }catch (AvroRuntimeException e){
            logger.error(e.getMessage(),e);
        }finally {
            if(fsDataOutputStream != null){
                fsDataOutputStream.close();
                this.fsDataOutputStream = null;
            }
            if(dataFileWriter != null){
                dataFileWriter.close();
                this.dataFileWriter = null;
            }
            closing = false;
        }
    }

    public void open() throws IOException{

        if(logger.isDebugEnabled()){
            logger.debug("@open "+this.filePath.toString());
        }

        FileSystem dfs = FileSystem.get(hdfsConfiguration);
        this.dataFileWriter = new DataFileWriter<>(datumWriter);
        if(dfs.exists(this.filePath)){
            this.fsDataOutputStream = dfs.append(this.filePath);
            this.dataFileWriter.appendTo(
                    new AvroFSInput(dfs.open(this.filePath),dfs.getFileStatus(this.filePath).getLen()),
                    this.fsDataOutputStream
            );
        }else{
            this.fsDataOutputStream = dfs.create(this.filePath);
            this.dataFileWriter.create(
                    this.schema,
                    this.fsDataOutputStream
            );
        }

    }
}
