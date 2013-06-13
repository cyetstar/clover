package org.cyetstar.clover.rest.douban;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DoubanMovieCredit {

	private DoubanCelebrity celebrity;

	public DoubanCelebrity getCelebrity() {
		return celebrity;
	}

	public void setCelebrity(DoubanCelebrity celebrity) {
		this.celebrity = celebrity;
	}

}
