package com.zhongjh.sardine.report;

import com.zhongjh.sardine.model.Multistatus;
import com.zhongjh.sardine.util.SardineUtil;

import java.io.IOException;

public abstract class SardineReport<T>
{
	public String toXml() throws IOException
	{
		return SardineUtil.toXml(toJaxb());
	}

	public abstract Object toJaxb();

	public abstract T fromMultistatus(Multistatus multistatus);
}
