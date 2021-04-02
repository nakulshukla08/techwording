package com.techwording.batch.lifecycle;

import java.nio.file.Paths;
import java.util.List;

import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.JsonObjectMarshaller;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class JsonWriterExt<T> extends JsonFileItemWriter<T> {

	public JsonWriterExt(String file, String name) {

		super(new FileSystemResource(Paths.get(file)
			.toString()), new JacksonJsonObjectMarshaller<>());
		super.setName(name);

	}

	public JsonWriterExt(Resource resource, JsonObjectMarshaller<T> jsonObjectMarshaller) {

		super(resource, jsonObjectMarshaller);

	}

	@Override
	public String doWrite(List<? extends T> items) {

		// TODO Auto-generated method stub
		String result = super.doWrite(items);

		System.out.println("Extension Logic");

		return result;
	}

}
