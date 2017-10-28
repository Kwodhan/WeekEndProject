# Use an official Python runtime as a parent image
FROM java:8

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
ADD target/WeekEndProject-0.0.1.jar /app

# Install any needed packages specified in requirements.txt
# Make port 80 available to the world outside this container
EXPOSE 8080

CMD ["java","-jar", "WeekEndProject-0.0.1.jar"]
