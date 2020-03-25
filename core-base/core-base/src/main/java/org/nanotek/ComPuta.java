package org.nanotek;

@FunctionalInterface
public interface ComPuta<$ extends Base<$>>{
     Result<?,?> ei(Money<$> money);
}
