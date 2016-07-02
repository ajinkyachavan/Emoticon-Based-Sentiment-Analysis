


args <- commandArgs(trailingOnly = TRUE)



value <- args[1]


print(value)



setwd("/home/tuss/workspace/EmoticonFuzzyCMeans/")



#print(paste(c( value," value"), collapse = "_"))
#print(paste(c(m," number"), collapse = "_"))




plotData <- function(value){

  
#  print(value)
 # print(m)
  
my <- value
  
print(my)



d <- read.table(my)


my <- as.expression(my)

filename <- strsplit(toString(my),"/")

filename <- filename[[1]][length(filename[[1]])]



setwd("/home/tuss/workspace/EmoticonFuzzyCMeans/svm")

name <- paste(c("barplot",filename, ".pdf"), collapse = "_")
pdf(name)


#plot(d,col="red",main=filename, pch=4, xlab = c("SVM","SVM+FCM"))


d1 <- as.matrix(d) 


#d <- c(8,1)

barplot(d1[,1],xlab="Techniques",ylab="Accuracy %", col=rainbow(2), names.arg = c("SVM","SVM+FCM"))

}

myPlot <- plotData(value)




