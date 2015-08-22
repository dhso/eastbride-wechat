package modules.codepad.kit;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Record;

public class TreeKit {
	public static List<Record> formatTree(List<Record> listingRecord, Integer pidStart) {
		List<Record> recordList = new ArrayList<Record>();
		for (Record r : listingRecord) {
			if (pidStart == r.getInt("pid")) {
				List<Record> childMap = formatTree(listingRecord, r.getInt("id"));
				if (childMap.size() > 0) {
					r.set("state", "closed");
					r.set("children", childMap);
				}
				recordList.add(r);
			}
		}
		return recordList;
	}
}
