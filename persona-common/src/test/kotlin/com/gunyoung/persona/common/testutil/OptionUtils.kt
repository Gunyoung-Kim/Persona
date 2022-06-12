package com.gunyoung.persona.common.testutil

import com.gunyoung.persona.common.model.AlarmOptionEntity


fun sampleAlarmOptionEntity(
    isSmsAlarmReceived: Boolean = true,
    isMailAlarmReceived: Boolean = true
): AlarmOptionEntity =
    AlarmOptionEntity(
        isSmsAlarmReceived, isMailAlarmReceived
    )