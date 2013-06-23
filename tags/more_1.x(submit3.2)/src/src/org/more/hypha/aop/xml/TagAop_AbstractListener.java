/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.more.hypha.aop.xml;
import org.more.core.xml.XmlStackDecorator;
import org.more.core.xml.stream.StartElementEvent;
import org.more.hypha.aop.define.AbstractInformed;
import org.more.hypha.aop.define.AopDefineInformed;
import org.more.hypha.aop.define.AopMethodInformed;
import org.more.hypha.aop.define.PointcutType;
import org.more.hypha.context.xml.XmlDefineResource;
/**
 * �����ǽ���Informed���ͱ�ǩ�Ļ��ࡣ
 * @version 2010-10-9
 * @author ������ (zyc@byshell.org)
 */
public abstract class TagAop_AbstractListener extends TagAop_AbstractInformed<AbstractInformed> {
    public TagAop_AbstractListener(XmlDefineResource configuration) {
        super(configuration);
    }
    /**����Informed���͡�*/
    protected abstract PointcutType getPointcutType();
    /**���ݱ�ǩ������Ϣ����һ��{@link AopDefineInformed}���Ͷ��������ǩ��������method�����򴴽�{@link AopMethodInformed}���ء�*/
    protected AbstractInformed createDefine(StartElementEvent event) {
        String method = event.getAttributeValue("method");
        if (method != null) {
            AopMethodInformed informed = new AopMethodInformed();
            informed.setMethod(method);
            return informed;
        } else
            return new AopDefineInformed();
    }
    /**��ʼ������ǩ��ȷ��PointcutType���ԡ�*/
    public void beginElement(XmlStackDecorator context, String xpath, StartElementEvent event) {
        super.beginElement(context, xpath, event);
        AbstractInformed define = this.getDefine(context);
        define.setPointcutType(this.getPointcutType());
    }
}