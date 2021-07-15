package br.com.freitas.adapter.elastic.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ElasticConstants {

    public static final String INDEX = "products";
    public static final String DOC = "/_doc/";
    public static final String UPDATE = "/_update/";
    public static final String METHOD_POST = "POST";
    public static final String METHOD_GET = "GET";
    public static final String METHOD_DELETE = "DELETE";
    public static final String METHOD_HEAD = "HEAD";

    public static final Integer STATUS_OK = 200;
    public static final Integer STATUS_CREATED = 201;
}
