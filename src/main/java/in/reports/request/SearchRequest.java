package in.reports.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SearchRequest {

	private String planName;

	private String planSatus;

	private String gender;

	private String startDate;

	private String endDate;

}
