package org.sothis.core.util.cron;

import java.util.Calendar;

public class Matchers {

	/**
	 * ƥ������
	 */
	public final static Matcher MATCH_ALL_MATHCER = new Matcher() {
		@Override
		public boolean matches(Calendar calendar, int field) {
			return true;
		}
	};

	/**
	 * ƥ�����ÿ�������һ��������
	 */
	public final static Matcher LAST_WORK_DAY_MATCHER = new Matcher() {
		@Override
		public boolean matches(Calendar calendar, int field) {
			int todayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
			if (todayOfWeek == Calendar.SUNDAY || todayOfWeek == Calendar.SATURDAY) {
				return false;
			}
			int lastDay = calendar.getActualMaximum(field);
			int today = calendar.get(field);
			if (todayOfWeek == Calendar.FRIDAY) {
				return today + 3 > lastDay;
			} else {
				return today + 1 > lastDay;
			}
		}
	};

	/**
	 * ƥ�䵥��ֵ
	 * 
	 * @param value
	 * @return
	 */
	public static Matcher valueMatcher(final int value) {
		return new Matcher() {
			@Override
			public boolean matches(Calendar calendar, int field) {
				return value == calendar.get(field);
			}
		};
	}

	/**
	 * ƥ�����ƫ�Ƶ��ظ��ֶ�
	 * 
	 * @param offset
	 * @param repeat
	 * @return
	 */
	public static Matcher offsetRepeatMatcher(final int offset, final int repeat) {
		return new Matcher() {
			@Override
			public boolean matches(Calendar calendar, int field) {
				return (calendar.get(field) - offset) % repeat == 0;
			}
		};
	}

	/**
	 * ƥ�����������ظ��ֶ�
	 * 
	 * @param range
	 * @param repeat
	 * @return
	 */
	public static Matcher rangeRepeatMatcher(final Range range, final int repeat) {
		return new Matcher() {
			@Override
			public boolean matches(Calendar calendar, int field) {
				int v = calendar.get(field);
				if (range.matches(calendar, field)) {
					if (range.getStart() <= range.getEnd()) {
						return (v - range.getStart()) % repeat == 0;
					} else {
						if (v >= range.getStart()) {
							return (v - range.getStart()) % repeat == 0;
						} else {
							return (v - range.getMin()) % repeat == 0;
						}
					}
				} else {
					return false;
				}
			}
		};
	}

	/**
	 * ƥ�����L(last)��ʶ���ֶ�
	 * 
	 * @param last
	 * @return
	 */
	public static Matcher lastMatcher(final int last) {
		return new Matcher() {
			@Override
			public boolean matches(Calendar calendar, int field) {
				if (field == Calendar.DAY_OF_MONTH) {
					return calendar.get(field) > calendar.getActualMaximum(field) - last;
				} else if (field == Calendar.DAY_OF_WEEK) {
					int dayOfWeek = calendar.get(field);
					if (dayOfWeek == last) {
						return calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH) < 7;
					} else {
						return false;
					}
				} else {
					return false;
				}
			}
		};
	}

	/**
	 * ƥ����С�#����ʶ���ֶ�
	 * 
	 * @param matcher
	 * @param week
	 * @return
	 */
	public static Matcher weekMatcher(final Matcher matcher, final int week) {
		return new Matcher() {
			@Override
			public boolean matches(Calendar calendar, int field) {
				if (matcher.matches(calendar, field)) {
					int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
					int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
					return (dayOfMonth - dayOfWeek + 1) / 7 + 1 == week;
				} else {
					return false;
				}
			}
		};
	}

	/**
	 * ƥ�乤�����ֶΣ�1W��
	 * 
	 * @param dayOfMonth
	 * @return
	 */
	public static Matcher nearestWorkDayMatcher(final int dayOfMonth) {
		return new Matcher() {
			@Override
			public boolean matches(Calendar calendar, int field) {
				int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
				int today = calendar.get(Calendar.DAY_OF_MONTH);
				if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY) {
					return false;
				} else if (today == dayOfMonth) {
					return true;
				} else if (dayOfWeek == Calendar.FRIDAY) {
					return (dayOfMonth == today + 1) || (dayOfMonth == calendar.getActualMaximum(Calendar.DAY_OF_MONTH) && dayOfMonth == today + 2);
				} else if (dayOfWeek == Calendar.MONDAY) {
					return (dayOfMonth == today - 1) || (dayOfMonth == 1 && dayOfMonth == today - 2);
				} else {
					return false;
				}
			}
		};
	}
}
