

args <- commandArgs(trailingOnly = TRUE)



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


#plot(d,col="red",main=filename, pch=4, xlab = "TFIDF Values")


d1 <- as.matrix(d) 

#names <- 

#d <- c(8,1)



barplot(d1[,1], main="SVM+FCM", ylab="Count", col=rainbow(2), names.arg = c("True Positives" ,"False Positives"))

}

myPlot <- plotData(value)

