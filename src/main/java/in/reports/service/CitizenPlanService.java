package in.reports.service;

import java.util.List;

import in.reports.entity.CitizenPlan;
import in.reports.request.SearchRequest;

public interface CitizenPlanService {

	/**
	 * 1) Method to get Plan Name dropdown data
	 */
	public List<String> getPlanNames();

	/**
	 * 2) Method to get Plan Status dropdown data
	 */
	public List<String> getPlanStatuses();

	/**
	 * 3) Method to handle search functionality (All search fields are optional)
	 */
	public List<CitizenPlan> search(SearchRequest request);

	/**
	 * 4) Method to export data to Excel file
	 */
	public boolean exportExcel();

	public boolean exportPdf();

}
