package com.fancy.mvvmdemo.view;

/**
 * @author pengkuanwang
 * @date 2019-08-21
 */
public class BindingCommand<T> {
    private BindingAction execute;
    private BindingFunction<Boolean> canExecute0;

    public BindingCommand(BindingAction execute) {
        this.execute = execute;
    }

    /**
     * 执行BindingAction命令
     */
    public void execute() {
        if (execute != null && canExecute0()) {
            execute.call();
        }
    }

    /**
     * 是否需要执行
     *
     * @return true则执行, 反之不执行
     */
    private boolean canExecute0() {
        if (canExecute0 == null) {
            return true;
        }
        return canExecute0.call();
    }
}
