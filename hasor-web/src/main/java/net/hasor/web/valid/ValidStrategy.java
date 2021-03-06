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
package net.hasor.web.valid;
/**
 * 验证器执行策略
 * @version : 2014年8月27日
 * @author 大妖怪 (1444916574@qq.com)
 */
public enum ValidStrategy {
    /** 停止执行后面的验证器 */
    EXIT_EXECUTION(0),//
    /** 接着执行后面的验证器(默认策略) */
    DEFAULT_CONTINUE(1);
    //
    private int value;
    ValidStrategy(int value) {
        this.value = value;
    }
    public Integer getValue() {
        return value;
    }
}