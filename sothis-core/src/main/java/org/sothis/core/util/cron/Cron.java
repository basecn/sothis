package org.sothis.core.util.cron;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * cron���ʽ
 * 
 * @author velna
 * 
 */
public class Cron {

	private final Field[] fields = new Field[7];
	private final String expression;

	private Cron(String expression) {
		if (null == expression) {
			throw new IllegalArgumentException("expression can not be null");
		}
		this.expression = expression;
		try {
			parse();
		} catch (NumberFormatException e) {
			throw new CronSyntaxException("error parse cron expression: " + expression, e);
		} catch (IllegalArgumentException e) {
			throw new CronSyntaxException("error parse cron expression: " + expression, e);
		}
	}

	/**
	 * ��һ��cron���ʽ����ΪCron����
	 * 
	 * @param cronExpression
	 * @throws CronSyntaxException
	 *             ����������
	 * @throws IllegalArgumentException
	 *             ���cronExpressionΪnull
	 * @return
	 */
	public static Cron compile(String cronExpression) {
		return new Cron(cronExpression);
	}

	private void parse() {
		String[] fieldSplit = expression.toUpperCase().split("\\s+");
		if (fieldSplit.length > 7 || fieldSplit.length < 6) {
			throw new CronSyntaxException("invalid cron expression: " + expression);
		}
		for (int i = 0; i < fieldSplit.length; i++) {
			String fieldExpr = fieldSplit[i];
			switch (i) {
			case 0:
				fields[i] = new SecondField(fieldExpr);
				break;
			case 1:
				fields[i] = new MinuteField(fieldExpr);
				break;
			case 2:
				fields[i] = new HourField(fieldExpr);
				break;
			case 3:
				fields[i] = new DayOfMonthField(fieldExpr);
				break;
			case 4:
				fields[i] = new MonthField(fieldExpr);
				break;
			case 5:
				fields[i] = new DayOfWeekField(fieldExpr);
				break;
			case 6:
				fields[i] = new YearField(fieldExpr);
				break;
			}
		}
	}

	/**
	 * �жϵ�ǰ��cron���ʽ�Ƿ�ƥ��һ��ʱ��
	 * 
	 * @param date
	 * @return trueΪƥ�䣬falseΪ��ƥ��
	 */
	public boolean matches(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		for (int i = 0; i < 7; i++) {
			Field field = fields[i];
			if (null != field && !field.isBlank() && !field.matches(calendar)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * �õ���һ��ƥ���ʱ�䣬���ص�ʱ���������ṩ��date֮��
	 * 
	 * @param date
	 * @return
	 */
	public Date next(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, 1);
		calendar.set(Calendar.MILLISECOND, 0);
		next0(calendar);
		return calendar.getTime();
	}

	private void next0(Calendar calendar) {
		List<Field> resets = new ArrayList<Field>();
		for (Field field : fields) {
			if (null != field && !field.isBlank()) {
				int amount = field.next(calendar);
				if (amount == 0) {
					resets.add(field);
				} else {
					for (Field rf : resets) {
						calendar.set(rf.getField(), rf.getMin());
					}
					next0(calendar);
				}
			}
		}
	}

	/**
	 * ԭʼ�ı��ʽ
	 * 
	 * @return
	 */
	public String getExpression() {
		return expression;
	}

	/**
	 * �õ������ĸ����ֶ�
	 * 
	 * @return
	 */
	public Field[] getFields() {
		return fields;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hashBuilder = new HashCodeBuilder().append(super.hashCode()).append(this.expression);
		return hashBuilder.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Cron)) {
			return false;
		}
		Cron cron = (Cron) obj;
		return cron.expression.equals(this.expression);
	}

}
