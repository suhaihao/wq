package com.bhst.wq.utils;

import com.bhst.wq.entity.WqActivityRecruitment;
import com.bhst.wq.service.WqActivityRecruitmentService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduledTaskUtils {
    private final WqActivityRecruitmentService wqActivityRecruitmentService;

    public ScheduledTaskUtils(WqActivityRecruitmentService wqActivityRecruitmentService) {
        this.wqActivityRecruitmentService = wqActivityRecruitmentService;
    }

    @Scheduled(cron = "0 0/5 * * * ?")
    public void Activity() {
        List<WqActivityRecruitment> list = wqActivityRecruitmentService.list();
        List<WqActivityRecruitment> updateList = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        for (WqActivityRecruitment wqActivityRecruitment : list) {
            if (Duration.between(wqActivityRecruitment.getStartTime(), now).toMinutes() > 0 && Duration.between(wqActivityRecruitment.getEndTime(), now).toMinutes() < 0) {
                if (wqActivityRecruitment.getStatus() != 1) {
                    wqActivityRecruitment.setStatus(1);
                    updateList.add(wqActivityRecruitment);
                }
            }
            if (Duration.between(wqActivityRecruitment.getEndTime(), now).toMinutes() > 0) {
                if (wqActivityRecruitment.getStatus() != 2) {
                    wqActivityRecruitment.setStatus(2);
                    updateList.add(wqActivityRecruitment);
                }
            }
        }
        if (!CollectionUtils.isEmpty(updateList)) {
            wqActivityRecruitmentService.updateBatchById(updateList);
        }
    }
}
