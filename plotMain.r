


args <- commandArgs(trailingOnly = TRUE)



value <- args[1]

#print(paste(c( value," value"), collapse = "_"))
#print(paste(c(m," number"), collapse = "_"))




plotData <- function(value){

  
  
my <- value
  
d <- read.table(my)


my <- as.expression(my)

filename <- strsplit(toString(my),"/")

filename <- filename[[1]][length(filename[[1]])]



setwd("/home/tuss/workspace/EmoticonFuzzyCMeans/svm")


name <- paste(c("plot", filename, ".pdf"), collapse = "_")
pdf(name)


plot(d,col="red",main=filename, pch=4, xlab = "Data Values")



}

myPlot <- plotData(value)




