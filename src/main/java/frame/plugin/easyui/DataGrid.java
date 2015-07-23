package frame.plugin.easyui;

import java.io.Serializable;
import java.util.List;

public class DataGrid implements Serializable {
	private static final long serialVersionUID = 6728106778595243014L;
	private String total;
	private List<?> rows;

	public DataGrid() {

	}

	public DataGrid(String total, List<?> rows) {
		this.total = total;
		this.rows = rows;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}
