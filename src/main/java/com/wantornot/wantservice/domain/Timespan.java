package com.wantornot.wantservice.domain;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Timespan {
	private ZonedDateTime startTime;
	private ZonedDateTime endTime;
}
