package org.sothis.core.util;

/**
 * һ���򵥵������в�������
 * 
 * @author velna
 * 
 */
public class GetOpt {
	private int index;
	private String value;
	private String name;
	private final String[] argv;

	public GetOpt(String[] argv) {
		this.argv = argv;
	}

	/**
	 * �õ���һ����ƥ��Ĳ�����argv�е�����
	 * 
	 * @return
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * �õ���ǰƥ��Ĳ�����ֵ
	 * 
	 * @return
	 */
	public String getValue() {
		return value;
	}

	/**
	 * �õ���ǰƥ��Ĳ�����
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * �õ���һ�²�����
	 * 
	 * @return ����-1��ʾû�и���Ĳ����ԣ��������������Ϊ1���ز��������ַ���������������ȴ���1�򷵻�0��
	 */
	public int next() {
		int ch = -1;
		this.name = null;
		this.value = null;
		while (ch == -1 && index < argv.length) {
			String s = argv[index++];
			if (s.startsWith("--")) {
				if (s.length() > 2) {
					int i = s.indexOf('=');
					ch = 0;
					if (i > 0) {
						this.name = s.substring(2, i);
						this.value = s.substring(i + 1);
					} else {
						this.name = s.substring(2);
					}
				}
			} else if (s.charAt(0) == '-') {
				if (s.length() > 1) {
					this.name = s.substring(1);
					if (this.name.length() == 1) {
						ch = this.name.charAt(0);
					} else {
						ch = 0;
					}
					if (index < argv.length && argv[index].charAt(0) != '-') {
						this.value = argv[index];
						index++;
					}
				}
			}
		}
		return ch;
	}

	public static void main(String[] args) {
		GetOpt getopt = new GetOpt(new String[] { "-h", "a", "--check=true", "abcde", "-list" });
		int ch;
		while ((ch = getopt.next()) != -1) {
			switch (ch) {
			case 'h':
				System.out.println("help=" + getopt.getValue());
				break;
			case 0:
				System.out.println(getopt.getName() + "=" + getopt.getValue());
				break;
			}
		}
	}
}
