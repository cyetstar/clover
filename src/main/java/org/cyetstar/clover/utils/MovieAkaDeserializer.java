package org.cyetstar.clover.utils;

import java.io.IOException;
import java.util.Set;

import org.cyetstar.clover.entity.MovieAka;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;


public class MovieAkaDeserializer extends JsonDeserializer<Set<MovieAka>> {

	@Override
	public Set<MovieAka> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException,
			JsonProcessingException {
		// TODO Auto-generated method stub
		// jp.nextToken();
		JsonToken t;

		while ((t = jp.nextToken()) != JsonToken.END_ARRAY) {

		}
		return null;
	}

}
