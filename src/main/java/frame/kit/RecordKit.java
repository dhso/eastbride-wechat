package frame.kit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Record;

public class RecordKit {

	/**
	 * list转RecordList
	 * 
	 * @param list
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Record> list2RecordList(List<?> list) {
		List<Record> recordList = new ArrayList<Record>();
		Iterator<?> it = list.iterator();
		while (it.hasNext()) {
			Map columns = (Map) it.next();
			Record record = new Record();
			record.setColumns(columns);
			recordList.add(record);
		}
		return recordList;
	}
}
