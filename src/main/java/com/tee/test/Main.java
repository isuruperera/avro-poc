package com.tee.test;

import com.tee.test.beans.*;
import org.apache.avro.Schema;
import org.apache.avro.SchemaNormalization;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.reflect.ReflectData;
import org.apache.avro.reflect.ReflectDatumReader;
import org.apache.avro.reflect.ReflectDatumWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        TestOuterBean testOuterBean = new TestOuterBean();
        populateTestData(testOuterBean);

        // Current schema returned by Avro reflect
        Schema schema = ReflectData.get().getSchema(TestOuterBean.class);

        // Extract the CRC 64 Avro fingerprint of the current schema
        byte[] fingerprint = SchemaNormalization.parsingFingerprint("CRC-64-AVRO", schema);
        String fingerprintString = bytesToHex(fingerprint);

        // Write the bean to a file, with fingerprint data
        writeBeanToFile(testOuterBean, fingerprintString);

        // Write the current schema to a file, file name as Avro fingerprint
        writeSchemaToFile(schema, fingerprintString);


        File readPath = new File("payloads");
        File[] readFiles = readPath.listFiles();

        // Test if previously written files can be read
        for (File file : Objects.requireNonNull(readFiles)) {
            readEncodedFile(file);
        }
    }

    private static void readEncodedFile(File file) throws IOException {
        byte[] bytesRead = Files.readAllBytes(file.toPath());

        // Extract the avro fingerprint from file name
        String readObjectFingerprintString = file.getName().split("_")[0];

        // Read the previous schema related to fingerprint
        Schema readObjectSchema = new Schema.Parser().parse(new File("schemas/" + readObjectFingerprintString + ".avro"));

        // Create the datum reader using the old schema
        ReflectDatumReader<TestOuterBean> datumReader = new ReflectDatumReader<>(TestOuterBean.class);
        datumReader.setSchema(readObjectSchema);

        Decoder decoder = DecoderFactory.get().binaryDecoder(bytesRead, null);
        TestOuterBean testOuterBeanRead = datumReader.read(null, decoder);

        System.out.println(testOuterBeanRead);
    }

    private static void writeSchemaToFile(Schema schema, String fingerprintString) throws IOException {
        Path pathSchema = Paths.get("schemas/" + fingerprintString + ".avro");
        Files.write(pathSchema, schema.toString().getBytes(StandardCharsets.UTF_8));
    }

    private static void writeBeanToFile(TestOuterBean testOuterBean, String fingerprintString) throws IOException {
        ReflectDatumWriter<TestOuterBean> datumWriter = new ReflectDatumWriter<>(TestOuterBean.class);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Encoder encoder = EncoderFactory.get().binaryEncoder(outputStream, null);
        datumWriter.write(testOuterBean, encoder);
        encoder.flush();

        byte[] serializedBytes = outputStream.toByteArray();

        Path writePath = Paths.get("payloads/" + fingerprintString + "_" + System.currentTimeMillis() + ".blob");
        Files.write(writePath, serializedBytes);
        System.out.println("Written payload: " + writePath);
    }

    private static void populateTestData(TestOuterBean testOuterBean) {
        TestL1InnerBean testL1InnerBean1 = new TestL1InnerBean();
        TestL1InnerBean testL1InnerBean2 = new TestL1InnerBean();

        TestL2InnerBean1Base testL2InnerBean11 = new TestL2InnerBean1Impl1();
        TestL2InnerBean1Base testL2InnerBean12 = new TestL2InnerBean1Impl2();
        TestL2InnerBean1Base testL2InnerBean13 = new TestL2InnerBean1Impl1();
        TestL2InnerBean1Base testL2InnerBean14 = new TestL2InnerBean1Impl2();
        TestL2InnerBean2 testL2InnerBean21 = new TestL2InnerBean2();
        TestL2InnerBean2 testL2InnerBean22 = new TestL2InnerBean2();

        testOuterBean.setL1InnerBeanList(Arrays.asList(testL1InnerBean1, testL1InnerBean2));

        testL1InnerBean1.setL2InnerBean1Set(new HashSet<>(Arrays.asList(testL2InnerBean11, testL2InnerBean12)));
        testL1InnerBean2.setL2InnerBean1Set(new HashSet<>(Arrays.asList(testL2InnerBean13, testL2InnerBean14)));

        testL1InnerBean1.setL2InnerBean2(testL2InnerBean21);
        testL1InnerBean2.setL2InnerBean2(testL2InnerBean22);

        testOuterBean.setProp1("T1");
        testOuterBean.setProp2(null);

        testL1InnerBean1.setTestBD(BigDecimal.TEN);

        testL1InnerBean2.setTestStr("TestStr2");

        testL2InnerBean11.setTestNum(2.33D);

        ((TestL2InnerBean1Impl2) testL2InnerBean12).setFish(4598543L);
        ((TestL2InnerBean1Impl1) testL2InnerBean13).setTestDate(new Date());

        testL2InnerBean13.setTestNum(4.4D);

        testL2InnerBean21.setTest3Int(100L);

        testOuterBean.setTestLD(LocalDate.now());
    }

    // Helper method to convert byte array to hexadecimal string
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            hexChars[i * 2] = HEX_ARRAY[v >>> 4];
            hexChars[i * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}