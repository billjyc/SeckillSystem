package org.seckill.exception;

import org.seckill.dto.SeckillExecution;

/**
 * 重复秒杀异常（运行期异常）
 * Created by billjyc on 2016/12/19.
 */
public class RepeatKillException extends SeckillException{

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
