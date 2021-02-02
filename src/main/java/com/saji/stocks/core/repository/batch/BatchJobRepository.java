package com.saji.stocks.core.repository.batch;

import com.saji.stocks.entities.batch.DefaultParameter;
import com.saji.stocks.entities.batch.DefaultParameterId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface BatchJobRepository extends CrudRepository<DefaultParameter, Long> {

    String GET_MOST_RECENT_EXEC_BY_JOB_AND_STATUS = "select bje.jobExecutionId "
            + "from BatchJobExecution bje where " + "bje.status = ?1" + " and bje.startTime = "
            + "(select max(bje2.startTime) from BatchJobExecution bje2,BatchJobInstance bji "
            + " where bje2.jobInstanceId = bji.jobInstanceId " + "and bje2.status =?1 " + "and bji.jobName =?2)";
    String GET_DEFAULT_FOR_JOB = "from DefaultParameter BPD where BPD.defaultParameterId.jobName = :jobName"
            + "and BPD.logicalDeleteInd ='N'";
    String UPDATE_STEP_EXECUTION_STATUS = "update batch_step_execution set status = ?1,exitCode=?2 where jobExecutionId = ?3";
    String UPDATE_JOB_EXECUTION_STATUS = "update BatchJobExecution set status = ?1,exitCode=?2 where jobExecutionId = ?3";

    @Query(GET_MOST_RECENT_EXEC_BY_JOB_AND_STATUS)
    Optional<Integer> getMostRecentJobExectionIdByStatusAndJobname(final String status, final String jobName);

    @Query(UPDATE_JOB_EXECUTION_STATUS)
    void updateStepExecution(final String status, final String exitCode, final BigInteger jeid);

    @Query(UPDATE_JOB_EXECUTION_STATUS)
    void updateJobExecution(final String status, final String exitCode, final BigInteger jeid);

    default void updateJobStepExecution(final String status, final String exitCode, final BigInteger jeid) {
        updateStepExecution(status, exitCode, jeid);
        updateJobExecution(status, exitCode, jeid);
    }

    @Query("from DefaultParameter where defaultParameterId.jobName = ?1 and logicalDeleteIn='N'")
    List<DefaultParameter> getBatchDefaultParameters(final String jobName);

    @Query("from DefaultParameter where defaultParameterId = ?1  and logicalDeleteIn='N'")
    DefaultParameter getBatchDefaultParameter(final DefaultParameterId id);

    default Optional<DefaultParameter> getCronExpression(final String jobName) {
        return Optional.ofNullable(getBatchDefaultParameter(new DefaultParameterId(jobName, "cronExpression")));
    }

    default DefaultParameter getBlockingStatus(final String jobName) {

        return Optional.ofNullable(getBatchDefaultParameter(new DefaultParameterId(jobName, "isBlocking")))
                .orElse(new DefaultParameter());
    }
}
