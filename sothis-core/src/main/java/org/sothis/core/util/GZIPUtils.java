package org.sothis.core.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZIPUtils {

	/**
	 * ��data��gzip��ʽѹ��������
	 * 
	 * @param data
	 * @return ����ѹ���������
	 * @throws IOException
	 */
	public static byte[] makeAsByteArray(byte[] data) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream(data.length);
		GZIPOutputStream zipOut = new GZIPOutputStream(out, Math.max(data.length / 10, 512));
		zipOut.write(data);
		zipOut.close();
		return out.toByteArray();
	}

	/**
	 * ��data��gzip��ʽѹ����������һ���ɶ�ȡ��InputStream
	 * 
	 * @param data
	 * @return ���ؿɶ���InputStream
	 * @throws IOException
	 */
	public static InputStream makeAsInputStream(byte[] data) throws IOException {
		return new ByteArrayInputStream(makeAsByteArray(data));
	}

	/**
	 * ��gzip��ʽ������inת���ɽ�ѹ�������
	 * 
	 * @param in
	 * @return ���ؽ�ѹ�������
	 * @throws IOException
	 */
	public static InputStream wrap(InputStream in) throws IOException {
		return new GZIPInputStream(in);
	}

	/**
	 * �����ת����gzipѹ��������
	 * 
	 * @param out
	 * @return ����gzipѹ��������
	 * @throws IOException
	 */
	public static OutputStream wrap(OutputStream out) throws IOException {
		return new GZIPOutputStream(out);
	}
}
