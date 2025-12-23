package in.reports.runner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.reports.entity.CitizenPlan;
import in.reports.repo.CitizenPlanRepository;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private CitizenPlanRepository repo;

	private static final String[] PLAN_NAMES = { "CASH", "FOOD", "MEDICAL", "EMPLOYMENT" };

	private static final String[] PLAN_STATUSES = { "APPROVED", "DENIED", "TERMINATED" };

	private static final String[] GENDERS = { "MALE", "FEMALE" };

	private static final String[] TERMINATION_REASONS = { "Policy violation", "User request", "Document expired" };

	@Override
	public void run(ApplicationArguments args) throws Exception {

		// ✅ Insert data only once
		if (repo.count() > 0) {
			return;
		}

		Random random = new Random();
		List<CitizenPlan> plans = new ArrayList<>();

		for (int i = 1; i <= 50; i++) {

			CitizenPlan plan = new CitizenPlan();

			// Common fields
			plan.setCitizenName("Citizen_" + i);
			plan.setGender(GENDERS[random.nextInt(GENDERS.length)]);
			plan.setPlanName(PLAN_NAMES[random.nextInt(PLAN_NAMES.length)]);

			String status = PLAN_STATUSES[random.nextInt(PLAN_STATUSES.length)];
			plan.setPlanStatus(status);

			// ================= APPROVED =================
			if ("APPROVED".equals(status)) {

				LocalDate startDate = LocalDate.now().minusDays(random.nextInt(1000));

				plan.setPlanStartDate(startDate);
				plan.setPlanEndDate(startDate.plusMonths(random.nextInt(12) + 1));

				plan.setBenefitAmount(5000.0 + random.nextInt(20000));

				plan.setDeniedReason(null);
				plan.setTerminationDate(null);
				plan.setTerminationReason(null);
			}

			// ================= DENIED =================
			else if ("DENIED".equals(status)) {

				plan.setPlanStartDate(null);
				plan.setPlanEndDate(null);
				plan.setBenefitAmount(null);

				plan.setDeniedReason("Not eligible");

				plan.setTerminationDate(null);
				plan.setTerminationReason(null);
			}

			// ================= TERMINATED =================
			else if ("TERMINATED".equals(status)) {

				LocalDate startDate = LocalDate.now().minusDays(random.nextInt(1000));

				plan.setPlanStartDate(startDate);
				plan.setPlanEndDate(null);

				plan.setBenefitAmount(null);
				plan.setDeniedReason(null);

				plan.setTerminationDate(startDate.plusMonths(random.nextInt(6) + 1));

				plan.setTerminationReason(TERMINATION_REASONS[random.nextInt(TERMINATION_REASONS.length)]);
			}

			plans.add(plan);
		}

		repo.saveAll(plans);

		System.out.println("✅ 50 records inserted correctly based on plan status");
	}
}
