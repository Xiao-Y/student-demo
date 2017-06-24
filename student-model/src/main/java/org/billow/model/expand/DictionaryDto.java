package org.billow.model.expand;

import java.io.Serializable;

import org.billow.model.domain.DictionaryBase;

/**
 * 数据字典
 * 
 * @author XiaoY
 * @date: 2015年9月20日 下午1:23:15
 */
public class DictionaryDto extends DictionaryBase implements Serializable {

	private static final long serialVersionUID = 2502088694584721393L;

	@Override
	public String toString() {
		return "DictionaryDto []" + super.toString();
	}
}
